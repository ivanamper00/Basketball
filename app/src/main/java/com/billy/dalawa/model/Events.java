package com.billy.dalawa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Events {

    @SerializedName("data")
    private List<Datum> data = null;

    public class Datum{
        @SerializedName("id")
        private String id;
        @SerializedName("slug")
        private String slug;
        @SerializedName("name")
        private String name;
        @SerializedName("status")
        private String status;
        @SerializedName("status_more")
        private String statusMore;
        @SerializedName("time_details")
        private Object timeDetails;
        @SerializedName("home_team")
        private HomeTeam homeTeam;
        @SerializedName("away_team")
        private AwayTeam awayTeam;
        @SerializedName("start_at")
        private String startAt;
        @SerializedName("priority")
        private Integer priority;
        @SerializedName("home_score")
        private HomeScore homeScore;
        @SerializedName("away_score")
        private HomeScore awayScore;
        @SerializedName("lasted_period")
        private String lastedPeriod;
        @SerializedName("default_period_count")
        private Integer defaultPeriodCount;
        @SerializedName("attendance")
        private Integer attendance;
        @SerializedName("cup_match_order")
        private Integer cupMatchOrder;
        @SerializedName("cup_match_in_round")
        private Integer cupMatchInRound;
        @SerializedName("league")
        private League league;
        @SerializedName("winner_code")
        private Integer winner_code;
        @SerializedName("main_stat")
        private Stats main_stat;

        public Datum(String id, String slug, String name, String status, String statusMore, Object timeDetails, HomeTeam homeTeam, AwayTeam awayTeam, String startAt, Integer priority, HomeScore homeScore, HomeScore awayScore, String lastedPeriod, Integer defaultPeriodCount, Integer attendance, Integer cupMatchOrder, Integer cupMatchInRound, League league, Integer winner_code, Stats main_stat) {
            this.id = id;
            this.slug = slug;
            this.name = name;
            this.status = status;
            this.statusMore = statusMore;
            this.timeDetails = timeDetails;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.startAt = startAt;
            this.priority = priority;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
            this.lastedPeriod = lastedPeriod;
            this.defaultPeriodCount = defaultPeriodCount;
            this.attendance = attendance;
            this.cupMatchOrder = cupMatchOrder;
            this.cupMatchInRound = cupMatchInRound;
            this.league = league;
            this.winner_code = winner_code;
            this.main_stat = main_stat;
        }

        public class Stats{
            @SerializedName("free_throws")
            private HomeAwayStat freeThrows;
            @SerializedName("2_pointers")
            private HomeAwayStat _2Pointers;
            @SerializedName("3_pointers")
            private HomeAwayStat _3Pointers;
            @SerializedName("field_goals")
            private HomeAwayStat fieldGoals;
            @SerializedName("rebounds")
            private HomeAwayStat rebounds;
            @SerializedName("fouls")
            private HomeAwayStat fouls;

            public class HomeAwayStat{
                @SerializedName("home")
                private Integer home;
                @SerializedName("away")
                private Integer away;

                public Integer getHome() {
                    return home;
                }

                public Integer getAway() {
                    return away;
                }
            }

            public HomeAwayStat getFreeThrows() {
                return freeThrows;
            }

            public HomeAwayStat get_2Pointers() {
                return _2Pointers;
            }

            public HomeAwayStat get_3Pointers() {
                return _3Pointers;
            }

            public HomeAwayStat getFieldGoals() {
                return fieldGoals;
            }

            public HomeAwayStat getRebounds() {
                return rebounds;
            }

            public HomeAwayStat getFouls() {
                return fouls;
            }
        }


        public class HomeTeam{
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
            @SerializedName("name_code")
            private String nameCode;
            @SerializedName("has_sub")
            private Boolean hasSub;
            @SerializedName("has_logo")
            private Boolean hasLogo;
            @SerializedName("logo")
            private String logo;
            @SerializedName("gender")
            private String gender;
            @SerializedName("is_nationality")
            private Boolean isNationality;
            @SerializedName("country_code")
            private String countryCode;
            @SerializedName("country")
            private String country;
            @SerializedName("flag")
            private String flag;
            @SerializedName("foundation")
            private String foundation;
            @SerializedName("details")
            private Object details;

            public HomeTeam(Integer id, String name, String nameFull, String logo, String country) {
                this.id = id;
                this.name = name;
                this.nameFull = nameFull;
                this.logo = logo;
                this.country = country;
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNameFull() {
                return nameFull;
            }

            public String getLogo() {
                return logo;
            }

            public String getCountry() {
                return country;
            }
        }

        public class AwayTeam{
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
            @SerializedName("name_code")
            private String nameCode;
            @SerializedName("has_sub")
            private Boolean hasSub;
            @SerializedName("has_logo")
            private Boolean hasLogo;
            @SerializedName("logo")
            private String logo;
            @SerializedName("gender")
            private String gender;
            @SerializedName("is_nationality")
            private Boolean isNationality;
            @SerializedName("country_code")
            private String countryCode;
            @SerializedName("country")
            private String country;
            @SerializedName("flag")
            private String flag;
            @SerializedName("foundation")
            private String foundation;
            @SerializedName("details")
            private Object details;

            public AwayTeam(Integer id, String name, String nameFull, String logo, String country) {
                this.id = id;
                this.name = name;
                this.nameFull = nameFull;
                this.logo = logo;
                this.country = country;
            }

            public Integer getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getNameFull() {
                return nameFull;
            }

            public String getLogo() {
                return logo;
            }

            public String getCountry() {
                return country;
            }
        }

        public class HomeScore{
            @SerializedName("current")
            private Integer current;
            @SerializedName("display")
            private String display;
            @SerializedName("period_1")
            private String period1;
            @SerializedName("period_2")
            private String period2;
            @SerializedName("period_3")
            private String period3;
            @SerializedName("period_4")
            private String period4;
            @SerializedName("normal_time")
            private Integer normalTime;

            public HomeScore(String display, String period1, String period2, String period3, String period4) {
                this.display = display;
                this.period1 = period1;
                this.period2 = period2;
                this.period3 = period3;
                this.period4 = period4;
            }

            public String getPeriod1() {
                return period1;
            }

            public String getPeriod2() {
                return period2;
            }

            public String getPeriod3() {
                return period3;
            }

            public String getPeriod4() {
                return period4;
            }

            public String getDisplay() {
                return display;
            }
        }

        public class League{
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
            @SerializedName("tennis_points")
            private Integer tennisPoints;
            @SerializedName("facts")
            private Object facts;

            public League(Integer id, String name, String logo, String startDate, String endDate) {
                this.id = id;
                this.name = name;
                this.logo = logo;
                this.startDate = startDate;
                this.endDate = endDate;
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
        }

        public String getSlug() {
            return slug;
        }

        public Object getTimeDetails() {
            return timeDetails;
        }

        public Integer getPriority() {
            return priority;
        }

        public String getLastedPeriod() {
            return lastedPeriod;
        }

        public Integer getDefaultPeriodCount() {
            return defaultPeriodCount;
        }

        public Integer getAttendance() {
            return attendance;
        }

        public Integer getCupMatchOrder() {
            return cupMatchOrder;
        }

        public Integer getCupMatchInRound() {
            return cupMatchInRound;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }

        public String getStatusMore() {
            return statusMore;
        }

        public HomeTeam getHomeTeam() {
            return homeTeam;
        }

        public AwayTeam getAwayTeam() {
            return awayTeam;
        }

        public String getStartAt() {
            return startAt;
        }

        public HomeScore getHomeScore() {
            return homeScore;
        }

        public HomeScore getAwayScore() {
            return awayScore;
        }

        public League getLeague() {
            return league;
        }

        public Integer getWinner_code() {
            return winner_code;
        }

        public Stats getMain_stat() {
            return main_stat;
        }
    }

    public List<Datum> getData() {
        return data;
    }
}
