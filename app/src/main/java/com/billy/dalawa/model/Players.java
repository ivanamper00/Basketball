package com.billy.dalawa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Players {

    @SerializedName("data")
    private List<Datum> data = null;

        public class Datum{

            @SerializedName("id")
            private Integer id;
            @SerializedName("slug")
            private String slug;
            @SerializedName("name")
            private String name;
            @SerializedName("name_short")
            private String nameShort;
            @SerializedName("has_photo")
            private Boolean hasPhoto;
            @SerializedName("photo")
            private String photo;
            @SerializedName("position")
            private String position;
            @SerializedName("age")
            private Integer age;
            @SerializedName("date_birth_at")
            private String dateBirthAt;
            @SerializedName("height")
            private Float height;
            @SerializedName("shirt_number")
            private Integer shirtNumber;
            @SerializedName("nationality_code")
            private String nationalityCode;
            @SerializedName("flag")
            private String flag;
            @SerializedName("main_team")
            private MainTeam mainTeam;

            public Datum(Integer id, String slug, String name, String nameShort, String photo, String position, Integer age, String dateBirthAt, Float height, Integer shirtNumber, String nationalityCode, String flag, MainTeam mainTeam) {
                this.id = id;
                this.slug = slug;
                this.name = name;
                this.nameShort = nameShort;
                this.photo = photo;
                this.position = position;
                this.age = age;
                this.dateBirthAt = dateBirthAt;
                this.height = height;
                this.shirtNumber = shirtNumber;
                this.nationalityCode = nationalityCode;
                this.flag = flag;
                this.mainTeam = mainTeam;
            }

                public class MainTeam{

                    @SerializedName("id")
                    private Integer id;
                    @SerializedName("slug")
                    private String slug;
                    @SerializedName("name")
                    private String name;
                    @SerializedName("name_short")
                    private String nameShort;
                    @SerializedName("name_full")
                    private String nameFull;
                    @SerializedName("logo")
                    private String logo;
                    @SerializedName("gender")
                    private String gender;
                    @SerializedName("country_code")
                    private String countryCode;
                    @SerializedName("country")
                    private String country;
                    @SerializedName("flag")
                    private String flag;

                    public MainTeam(Integer id, String slug, String name, String nameShort, String nameFull, String logo, String gender, String countryCode, String country, String flag) {
                        this.id = id;
                        this.slug = slug;
                        this.name = name;
                        this.nameShort = nameShort;
                        this.nameFull = nameFull;
                        this.logo = logo;
                        this.gender = gender;
                        this.countryCode = countryCode;
                        this.country = country;
                        this.flag = flag;
                    }

                    public Integer getId() {
                        return id;
                    }

                    public String getSlug() {
                        return slug;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getNameShort() {
                        return nameShort;
                    }

                    public String getNameFull() {
                        return nameFull;
                    }

                    public String getLogo() {
                        return logo;
                    }

                    public String getGender() {
                        return gender;
                    }

                    public String getCountryCode() {
                        return countryCode;
                    }

                    public String getCountry() {
                        return country;
                    }

                    public String getFlag() {
                        return flag;
                    }
                }

            public Integer getId() {
                return id;
            }

            public String getSlug() {
                return slug;
            }

            public String getName() {
                return name;
            }

            public String getNameShort() {
                return nameShort;
            }

            public Boolean getHasPhoto() {
                return hasPhoto;
            }

            public String getPhoto() {
                return photo;
            }

            public String getPosition() {
                return position;
            }

            public Integer getAge() {
                return age;
            }

            public String getDateBirthAt() {
                return dateBirthAt;
            }

            public Float getHeight() {
                return height;
            }

            public Integer getShirtNumber() {
                return shirtNumber;
            }

            public String getNationalityCode() {
                return nationalityCode;
            }

            public String getFlag() {
                return flag;
            }

            public MainTeam getMainTeam() {
                return mainTeam;
            }
        }
        public List<Datum> getData() {
            return data;
        }
}
