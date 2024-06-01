package it.sportandreview.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Sha256Utils {

    private Sha256Utils() {
    }

    public static String encodeUuidWithSha256(String uuid) {
        return DigestUtils.sha256Hex(uuid);
    }

    public static String encodeUUID(String uuid) {
        try {
            return Sha256Utils.encodeUuidWithSha256(UUID.fromString(uuid).toString());
        } catch (IllegalArgumentException ignored) {
            return uuid;
        }
    }

    public static String getEncodedRandomUUID() {
        return Sha256Utils.encodeUUID(UUID.randomUUID().toString());
    }

}
