package ninhn.app.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import ninhn.app.until.DefaultUntil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ninhn.app.constant.SystemConstant.UPLOAD_PUBLIC;
import static ninhn.app.constant.SystemConstant.BLANK;
import static ninhn.app.constant.SystemConstant.EXTEND_JPG;
import static ninhn.app.constant.SystemConstant.EXTEND_PNG;

@Service
public class S3Wrapper {

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Autowired
    private PhotoService photoService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private String fileUrl;
    private String fileName;

    private PutObjectResult upload(MultipartFile multipartFile, String uploadKey) {
        //Update name of photo
        String uploadKeyDB = (new Date().getTime()) + "_" + uploadKey;

        try {
            InputStream inputStreamHandle = multipartFile.getInputStream();
            byte[] contentBytes = IOUtils.toByteArray(inputStreamHandle);
            Long contentLength = Long.valueOf(contentBytes.length);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, UPLOAD_PUBLIC + uploadKeyDB, multipartFile.getInputStream(), metadata);

            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

            PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

            IOUtils.closeQuietly(inputStreamHandle);

            fileUrl = amazonS3Client.getUrl(bucket, UPLOAD_PUBLIC + uploadKeyDB).toString();
            fileName = uploadKey.replace(EXTEND_JPG, BLANK);
            fileName = fileName.replace(EXTEND_PNG, BLANK);
            //Save photo to DB
            this.photoService.save(DefaultUntil.photoInsert(fileUrl, fileName));

            return putObjectResult;
        }catch (IOException ioException){
            return null;
        }
    }

    public List<PutObjectResult> upload(MultipartFile[] multipartFiles) {
        List<PutObjectResult> putObjectResults = new ArrayList<>();

        Arrays.stream(multipartFiles)
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    putObjectResults.add(upload(multipartFile, multipartFile.getOriginalFilename()));
                });

        return putObjectResults;
    }

    public ResponseEntity<byte[]> download(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

        S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public List<S3ObjectSummary> list() {
        ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

        return s3ObjectSummaries;
    }
}
