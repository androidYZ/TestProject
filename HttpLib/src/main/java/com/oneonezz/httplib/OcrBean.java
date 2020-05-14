package com.oneonezz.httplib;

import java.util.List;

/**
 * Created by ${Justin} on 2020/5/8.
 */
public class OcrBean {


    private int code;
    private String message;
    private String seq;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String session_id;
        private int angle;
        private List<ItemsBean> items;
        private List<?> question_block;
        private List<?> classX;
        private List<?> recognize_warn_code;
        private List<?> recognize_warn_msg;
        private List<?> recognize_warn_code_conf;

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public List<?> getQuestion_block() {
            return question_block;
        }

        public void setQuestion_block(List<?> question_block) {
            this.question_block = question_block;
        }

        public List<?> getClassX() {
            return classX;
        }

        public void setClassX(List<?> classX) {
            this.classX = classX;
        }

        public List<?> getRecognize_warn_code() {
            return recognize_warn_code;
        }

        public void setRecognize_warn_code(List<?> recognize_warn_code) {
            this.recognize_warn_code = recognize_warn_code;
        }

        public List<?> getRecognize_warn_msg() {
            return recognize_warn_msg;
        }

        public void setRecognize_warn_msg(List<?> recognize_warn_msg) {
            this.recognize_warn_msg = recognize_warn_msg;
        }

        public List<?> getRecognize_warn_code_conf() {
            return recognize_warn_code_conf;
        }

        public void setRecognize_warn_code_conf(List<?> recognize_warn_code_conf) {
            this.recognize_warn_code_conf = recognize_warn_code_conf;
        }

        public static class ItemsBean {
            /**
             * itemstring : 3画
             * itemcoord : {"x":380,"y":114,"width":75,"height":38}
             * words : [{"character":"3","confidence":0.9967760443687439},{"character":"画","confidence":0.9999662637710571}]
             * candword : []
             */

            private String itemstring;
            private ItemcoordBean itemcoord;
            private List<WordsBean> words;
            private List<?> candword;

            public String getItemstring() {
                return itemstring;
            }

            public void setItemstring(String itemstring) {
                this.itemstring = itemstring;
            }

            public ItemcoordBean getItemcoord() {
                return itemcoord;
            }

            public void setItemcoord(ItemcoordBean itemcoord) {
                this.itemcoord = itemcoord;
            }

            public List<WordsBean> getWords() {
                return words;
            }

            public void setWords(List<WordsBean> words) {
                this.words = words;
            }

            public List<?> getCandword() {
                return candword;
            }

            public void setCandword(List<?> candword) {
                this.candword = candword;
            }

            public static class ItemcoordBean {
                /**
                 * x : 380
                 * y : 114
                 * width : 75
                 * height : 38
                 */

                private int x;
                private int y;
                private int width;
                private int height;

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class WordsBean {
                /**
                 * character : 3
                 * confidence : 0.9967760443687439
                 */

                private String character;
                private double confidence;

                public String getCharacter() {
                    return character;
                }

                public void setCharacter(String character) {
                    this.character = character;
                }

                public double getConfidence() {
                    return confidence;
                }

                public void setConfidence(double confidence) {
                    this.confidence = confidence;
                }
            }
        }
    }
}
