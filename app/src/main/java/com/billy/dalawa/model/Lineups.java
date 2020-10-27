package com.billy.dalawa.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Lineups {
    @SerializedName("data")
    private List<Datum> data = null;

    public static class Datum{
        @SerializedName("id")
        private Integer id;
        @SerializedName("lineup_players")
        private List<LineupPlayer> lineupPlayers = null;

        public static class LineupPlayer{
            @SerializedName("id")
            private Integer id;
            @SerializedName("position")
            private Object position;
            @SerializedName("shirt_number")
            private Integer shirtNumber;
            @SerializedName("substitute")
            private Boolean substitute;
            @SerializedName("position_name")
            private String positionName;
            @SerializedName("position_key")
            private String positionKey;
            @SerializedName("is_captain")
            private Boolean isCaptain;
            @SerializedName("player")
            private Player player;

            public static class Player{
                @SerializedName("id")
                private Integer id;
                @SerializedName("slug")
                private String slug;
                @SerializedName("name")
                private String name;
                @SerializedName("photo")
                private String photo;
                @SerializedName("shirt_number")
                private Integer shirtNumber;

                public Integer getId() {
                    return id;
                }

                public String getSlug() {
                    return slug;
                }

                public String getName() {
                    return name;
                }

                public String getPhoto() {
                    return photo;
                }

                public Integer getShirtNumber() {
                    return shirtNumber;
                }
            }

            public Integer getId() {
                return id;
            }

            public Object getPosition() {
                return position;
            }

            public Integer getShirtNumber() {
                return shirtNumber;
            }

            public Boolean getSubstitute() {
                return substitute;
            }

            public String getPositionName() {
                return positionName;
            }

            public String getPositionKey() {
                return positionKey;
            }

            public Boolean getCaptain() {
                return isCaptain;
            }

            public Player getPlayer() {
                return player;
            }
        }

        public Integer getId() {
            return id;
        }

        public List<LineupPlayer> getLineupPlayers() {
            return lineupPlayers;
        }
    }

    public List<Datum> getData() {
        return data;
    }
}
