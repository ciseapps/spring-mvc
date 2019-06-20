package com.cise.cms.core.applications.constants;

public class AppConstants {

    public static class Proc {

        public static final int newRequest = 1; // -> assign vendor
        public static final int apvKdep = 2; // -> waiting assign invoice order
        public static final int apvProc = 3; // -> waiting approval kdep, barang sudah di order, tunggu barang datang
        public static final int apvKdepProc = 4;
        public static final int apvRcv = 5;
        public static final int apvKdepRcv = 6;
        public static final int apvPickup = 7;
        // public static final int finish = 8;

        public static final int reject = 20;

    }
}
