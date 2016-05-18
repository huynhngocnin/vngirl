package ninhn.app.until;

import java.util.Random;

import static ninhn.app.constant.SystemConstant.PHOTOS_IN_RANDOM;

/**
 * Created by ninhn on 5/18/2016.
 */
public class RandomUntil {
    public static int getRandom(int max, int records) {
        Random random = new Random();
        return random.nextInt(max - records);
    }

    public static int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static int getRandomPhotoPage(long max) {
        Random random = new Random();
        if (max > Integer.MAX_VALUE) {
            return random.nextInt(Integer.MAX_VALUE / PHOTOS_IN_RANDOM - 1);
        }
        return random.nextInt((int) max / PHOTOS_IN_RANDOM - 1);
    }
}
