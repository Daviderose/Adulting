package org.launchcode.Adulting.hashing;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SHA256Hashing {

    // class for SHA256 hashing
    public static String HashWithGuava(final String originalString) {
        final String sha256hex = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
        return sha256hex;
    }

}
