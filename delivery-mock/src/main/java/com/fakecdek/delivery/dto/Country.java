package com.fakecdek.delivery.dto;

public enum Country {
    USA("US", "United States"),
    CANADA("CA", "Canada"),
    UK("GB", "United Kingdom"),
    GERMANY("DE", "Germany"),
    FRANCE("FR", "France"),
    AUSTRALIA("AU", "Australia"),
    INDIA("IN", "India"),
    JAPAN("JP", "Japan"),
    CHINA("CN", "China"),
    SOUTH_KOREA("KR", "South Korea"),
    BRAZIL("BR", "Brazil"),
    MEXICO("MX", "Mexico"),
    RUSSIA("RU", "Russia"),
    BELARUS("BU", "Belarus"),
    SOUTH_AFRICA("ZA", "South Africa"),
    NIGERIA("NG", "Nigeria"),
    EGYPT("EG", "Egypt"),
    ARGENTINA("AR", "Argentina"),
    SAUDI_ARABIA("SA", "Saudi Arabia"),
    SPAIN("ES", "Spain"),
    ITALY("IT", "Italy"),
    SWITZERLAND("CH", "Switzerland"),
    SWEDEN("SE", "Sweden"),
    NORWAY("NO", "Norway"),
    NETHERLANDS("NL", "Netherlands"),
    BELGIUM("BE", "Belgium"),
    AUSTRIA("AT", "Austria"),
    DENMARK("DK", "Denmark"),
    FINLAND("FI", "Finland"),
    PORTUGAL("PT", "Portugal"),
    IRELAND("IE", "Ireland");

    private final String countryCode;
    private final String countryName;

    Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public static Country fromCountryCode(String countryCode) {
        for (Country country : Country.values()) {
            if (country.getCountryCode().equals(countryCode)) {
                return country;
            }
        }
        return null; // Or throw an exception if needed
    }

    public static Country fromCountryName(String countryName) {
        for (Country country : Country.values()) {
            if (country.getCountryName().equalsIgnoreCase(countryName)) {
                return country;
            }
        }
        return null;
    }
}
