package com.billy.dalawa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leagues {

        @SerializedName("data")
        private List<Datum> data = null;

        public class Datum{
            @SerializedName("id")
            private Integer id;
            @SerializedName("slug")
            private String slug;
            @SerializedName("name")
            private String name;
            @SerializedName("has_logo")
            private Boolean hasLogo;
            @SerializedName("logo")
            private String logo;
            @SerializedName("start_date")
            private String startDate;
            @SerializedName("end_date")
            private String endDate;
            @SerializedName("priority")
            private Integer priority;
            @SerializedName("host")
            private Host host;
            @SerializedName("tennis_points")
            private Integer tennisPoints;
            @SerializedName("facts")
            private List<Fact> facts = null;

            public Datum(Integer id, String name, String logo, String startDate, String endDate, Host host, List<Fact> facts) {
                this.id = id;
                this.name = name;
                this.logo = logo;
                this.startDate = startDate;
                this.endDate = endDate;
                this.host = host;
                this.facts = facts;
            }

                public class Host{
                    @SerializedName("country")
                    private String country;
                    @SerializedName("flag")
                    private String flag;

                    public Host(String country, String flag) {
                        this.country = country;
                        this.flag = flag;
                    }

                    public String getCountry() {
                        return country;
                    }

                    public String getFlag() {
                        return flag;
                    }
                }

                public class Fact{
                    @SerializedName("name")
                    private String name;
                    @SerializedName("value")
                    private String value;

                    public Fact(String name, String value) {
                        this.name = name;
                        this.value = value;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getValue() {
                        return value;
                    }
                }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getLogo() {
                return logo;
            }

            public String getStartDate() {
                return startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public Host getHost() {
                return host;
            }

            public List<Fact> getFacts() {
                return facts;
            }
        }
    public List<Datum> getData() {
            return data;
        }
}
