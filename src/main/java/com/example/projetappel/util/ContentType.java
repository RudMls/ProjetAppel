package com.example.projetappel.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentType {
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_PNG = "image/png";
    public static final String APPLICATION_PDF = "application/pdf";
    public static final Set<String> ANY_IMAGE_TYPE = new HashSet<>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/jfif"));
}
