package ninhn.app.controller;

import com.google.api.services.storage.model.StorageObject;
import ninhn.app.constant.ControllerConstant;
import ninhn.app.model.PhotoReview;
import ninhn.app.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


/**
 * Created by NinHN on 6/11/16.
 */
@RestController
@RequestMapping("/api/gcp/storage/public")
public class UploadController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = ControllerConstant.UPLOAD, method = RequestMethod.POST)
    public List<StorageObject> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        return this.storageService.uploads(multipartFiles);
    }

    @RequestMapping(value = ControllerConstant.LIST, method = RequestMethod.GET)
    public List<StorageObject> list() throws IOException, GeneralSecurityException {
        return this.storageService.listBucket();
    }

    @RequestMapping(value = "/user-photo-upload")
    public StorageObject userUploadPhoto(@RequestParam("photo-file") MultipartFile multipartFile, @RequestParam("photo-info") String strPhotoReview) {
        return this.storageService.userUploadPhoto(multipartFile, strPhotoReview);
    }

}
