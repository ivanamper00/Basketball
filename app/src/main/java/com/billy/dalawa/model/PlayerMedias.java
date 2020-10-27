package com.billy.dalawa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerMedias {

    @SerializedName("data")
    private List<Datum> data = null;

    public class Datum{
        @SerializedName("id")
        private Integer id;
        @SerializedName("source_type")
        private String sourceType;
        @SerializedName("source_id")
        private Integer sourceId;
        @SerializedName("type")
        private Integer type;
        @SerializedName("title")
        private String title;
        @SerializedName("sub_title")
        private String subTitle;
        @SerializedName("url")
        private String url;
        @SerializedName("source_url")
        private String sourceUrl;
        @SerializedName("thumbnail_url")
        private String thumbnailUrl;
        @SerializedName("source")
        private Source source;

        public Datum(Integer id, String sourceType, Integer sourceId, Integer type, String title, String subTitle, String url, String sourceUrl, String thumbnailUrl, Source source) {
            this.id = id;
            this.sourceType = sourceType;
            this.sourceId = sourceId;
            this.type = type;
            this.title = title;
            this.subTitle = subTitle;
            this.url = url;
            this.sourceUrl = sourceUrl;
            this.thumbnailUrl = thumbnailUrl;
            this.source = source;
        }

            public class Source{
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
                @SerializedName("weight")
                private Object weight;
                @SerializedName("age")
                private Integer age;
                @SerializedName("date_birth_at")
                private String dateBirthAt;
                @SerializedName("height")
                private Float height;
                @SerializedName("shirt_number")
                private Integer shirtNumber;
                @SerializedName("preferred_foot")
                private Object preferredFoot;
                @SerializedName("nationality_code")
                private String nationalityCode;
                @SerializedName("flag")
                private String flag;
                @SerializedName("market_currency")
                private String marketCurrency;
                @SerializedName("market_value")
                private Object marketValue;
                @SerializedName("contract_until")
                private String contractUntil;
                @SerializedName("rating")
                private Object rating;
                @SerializedName("characteristics")
                private Object characteristics;
                @SerializedName("positions")
                private Object positions;
                @SerializedName("ability")
                private Object ability;

                public Source(Integer id, String name, String photo, String position, Integer age, String dateBirthAt, Float height, Integer shirtNumber, String nationalityCode) {
                    this.id = id;
                    this.name = name;
                    this.photo = photo;
                    this.position = position;
                    this.age = age;
                    this.dateBirthAt = dateBirthAt;
                    this.height = height;
                    this.shirtNumber = shirtNumber;
                    this.nationalityCode = nationalityCode;
                }

                public Integer getId() {
                    return id;
                }

                public String getName() {
                    return name;
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
            }

        public Integer getId() {
            return id;
        }

        public String getSourceType() {
            return sourceType;
        }

        public Integer getSourceId() {
            return sourceId;
        }

        public Integer getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getUrl() {
            return url;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public Source getSource() {
            return source;
        }
    }

    public List<Datum> getData() {
        return data;
    }
}
