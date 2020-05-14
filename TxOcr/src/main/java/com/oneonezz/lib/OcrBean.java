package com.oneonezz.lib;

import java.util.List;

/**
 * Created by ${Justin} on 2020/5/8.
 */
public class OcrBean {

    /**
     * TextDetections : [{"DetectedText":"1画","Confidence":99,"Polygon":[{"X":29,"Y":53},{"X":35,"Y":97},{"X":118,"Y":84},{"X":111,"Y":40}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":1}}","ItemPolygon":{"X":29,"Y":40,"Width":90,"Height":58}},{"DetectedText":"一飞2面八七十一刀刁丁","Confidence":75,"Polygon":[{"X":176,"Y":118},{"X":176,"Y":30},{"X":1697,"Y":31},{"X":1697,"Y":118}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":1}}","ItemPolygon":{"X":176,"Y":30,"Width":1522,"Height":89}},{"DetectedText":"儿二几九了力乃也七人入","Confidence":86,"Polygon":[{"X":33,"Y":215},{"X":33,"Y":303},{"X":1719,"Y":294},{"X":1719,"Y":207}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":1}}","ItemPolygon":{"X":33,"Y":207,"Width":1687,"Height":97}},{"DetectedText":"---","Confidence":54,"Polygon":[{"X":371,"Y":339},{"X":374,"Y":411},{"X":753,"Y":392},{"X":749,"Y":320}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":2}}","ItemPolygon":{"X":371,"Y":320,"Width":383,"Height":92}},{"DetectedText":"**","Confidence":95,"Polygon":[{"X":1543,"Y":358},{"X":1543,"Y":344},{"X":1577,"Y":345},{"X":1576,"Y":359}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":3}}","ItemPolygon":{"X":1543,"Y":344,"Width":35,"Height":16}},{"DetectedText":"十义又3画才采彳于川寸大","Confidence":78,"Polygon":[{"X":30,"Y":488},{"X":30,"Y":373},{"X":1698,"Y":373},{"X":1698,"Y":488}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":30,"Y":373,"Width":1669,"Height":116}},{"DetectedText":"凡飞干个工弓广及已于中","Confidence":80,"Polygon":[{"X":33,"Y":668},{"X":33,"Y":548},{"X":1690,"Y":554},{"X":1689,"Y":674}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":33,"Y":548,"Width":1658,"Height":127}},{"DetectedText":"久孓口亏马么门女乞千刃","Confidence":94,"Polygon":[{"X":29,"Y":842},{"X":29,"Y":737},{"X":1694,"Y":744},{"X":1693,"Y":848}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":29,"Y":737,"Width":1666,"Height":112}},{"DetectedText":"三山上勺尸士已土丸万亡","Confidence":93,"Polygon":[{"X":28,"Y":925},{"X":28,"Y":1026},{"X":1697,"Y":1026},{"X":1697,"Y":925}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":28,"Y":925,"Width":1670,"Height":102}},{"DetectedText":"卫兀夕习下乡一小丫么也已","Confidence":82,"Polygon":[{"X":29,"Y":1100},{"X":29,"Y":1206},{"X":1712,"Y":1205},{"X":1712,"Y":1099}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":29,"Y":1099,"Width":1684,"Height":108}},{"DetectedText":"弋亿义于与丈之子","Confidence":89,"Polygon":[{"X":26,"Y":1386},{"X":26,"Y":1280},{"X":1222,"Y":1281},{"X":1222,"Y":1387}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":4}}","ItemPolygon":{"X":26,"Y":1280,"Width":1197,"Height":108}},{"DetectedText":"一巴J办","Confidence":67,"Polygon":[{"X":1426,"Y":1293},{"X":1427,"Y":1375},{"X":1698,"Y":1373},{"X":1698,"Y":1290}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":5}}","ItemPolygon":{"X":1426,"Y":1290,"Width":273,"Height":86}},{"DetectedText":"贝比币卞不仓车尺仇丑从","Confidence":99,"Polygon":[{"X":33,"Y":1466},{"X":33,"Y":1563},{"X":1698,"Y":1562},{"X":1698,"Y":1465}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":6}}","ItemPolygon":{"X":33,"Y":1465,"Width":1666,"Height":99}},{"DetectedText":"歹丹邓仃订斗队厄乏反方","Confidence":99,"Polygon":[{"X":35,"Y":1732},{"X":36,"Y":1614},{"X":1694,"Y":1640},{"X":1692,"Y":1758}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":6}}","ItemPolygon":{"X":35,"Y":1614,"Width":1660,"Height":145}},{"DetectedText":"!","Confidence":93,"Polygon":[{"X":79,"Y":1813},{"X":156,"Y":1784},{"X":133,"Y":1723},{"X":56,"Y":1752}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":6}}","ItemPolygon":{"X":56,"Y":1723,"Width":101,"Height":91}},{"DetectedText":"!","Confidence":71,"Polygon":[{"X":1626,"Y":1808},{"X":1719,"Y":1822},{"X":1723,"Y":1783},{"X":1632,"Y":1769}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":7}}","ItemPolygon":{"X":1626,"Y":1769,"Width":98,"Height":54}},{"DetectedText":"分丰风风夫父讣丐冈戈公","Confidence":93,"Polygon":[{"X":25,"Y":1822},{"X":25,"Y":1933},{"X":1706,"Y":1928},{"X":1706,"Y":1817}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":8}}","ItemPolygon":{"X":25,"Y":1817,"Width":1682,"Height":117}},{"DetectedText":"勾夫互户-化幻火讥计见介","Confidence":96,"Polygon":[{"X":30,"Y":2103},{"X":30,"Y":2002},{"X":1712,"Y":2005},{"X":1712,"Y":2106}],"AdvancedInfo":"{\"Parag\":{\"ParagNo\":8}}","ItemPolygon":{"X":30,"Y":2002,"Width":1683,"Height":105}}]
     * Angel : 0.0
     * RequestId : 45ae4180-0e4a-4b15-abb2-5622dd7545ee
     */

    private double Angel;
    private String RequestId;
    private List<TextDetectionsBean> TextDetections;

    public double getAngel() {
        return Angel;
    }

    public void setAngel(double Angel) {
        this.Angel = Angel;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    public List<TextDetectionsBean> getTextDetections() {
        return TextDetections;
    }

    public void setTextDetections(List<TextDetectionsBean> TextDetections) {
        this.TextDetections = TextDetections;
    }

    public static class TextDetectionsBean {
        /**
         * DetectedText : 1画
         * Confidence : 99
         * Polygon : [{"X":29,"Y":53},{"X":35,"Y":97},{"X":118,"Y":84},{"X":111,"Y":40}]
         * AdvancedInfo : {"Parag":{"ParagNo":1}}
         * ItemPolygon : {"X":29,"Y":40,"Width":90,"Height":58}
         */

        private String DetectedText;
        private int Confidence;
        private String AdvancedInfo;
        private ItemPolygonBean ItemPolygon;
        private List<PolygonBean> Polygon;

        public String getDetectedText() {
            return DetectedText;
        }

        public void setDetectedText(String DetectedText) {
            this.DetectedText = DetectedText;
        }

        public int getConfidence() {
            return Confidence;
        }

        public void setConfidence(int Confidence) {
            this.Confidence = Confidence;
        }

        public String getAdvancedInfo() {
            return AdvancedInfo;
        }

        public void setAdvancedInfo(String AdvancedInfo) {
            this.AdvancedInfo = AdvancedInfo;
        }

        public ItemPolygonBean getItemPolygon() {
            return ItemPolygon;
        }

        public void setItemPolygon(ItemPolygonBean ItemPolygon) {
            this.ItemPolygon = ItemPolygon;
        }

        public List<PolygonBean> getPolygon() {
            return Polygon;
        }

        public void setPolygon(List<PolygonBean> Polygon) {
            this.Polygon = Polygon;
        }

        public static class ItemPolygonBean {
            /**
             * X : 29
             * Y : 40
             * Width : 90
             * Height : 58
             */

            private int X;
            private int Y;
            private int Width;
            private int Height;

            public int getX() {
                return X;
            }

            public void setX(int X) {
                this.X = X;
            }

            public int getY() {
                return Y;
            }

            public void setY(int Y) {
                this.Y = Y;
            }

            public int getWidth() {
                return Width;
            }

            public void setWidth(int Width) {
                this.Width = Width;
            }

            public int getHeight() {
                return Height;
            }

            public void setHeight(int Height) {
                this.Height = Height;
            }
        }

        public static class PolygonBean {
            /**
             * X : 29
             * Y : 53
             */

            private int X;
            private int Y;

            public int getX() {
                return X;
            }

            public void setX(int X) {
                this.X = X;
            }

            public int getY() {
                return Y;
            }

            public void setY(int Y) {
                this.Y = Y;
            }
        }
    }
}
