package github.banana.demo;

/**
 * 供应商类型和描述
 */
public enum  EnumTP {
    CTRIP("1", "携程"),
    CA("2", "国航"),
    MU("4", "东航"),
    CZ("8", "南航"),
    CTRIPH5("16", "携程一期H5"),
    BIQU("32", "必去"),
    CTRIPCRAWL("64", "携程H5抓取"),
    BIQUINT("128", "必去国际"),
    BIQUSTANDARD("256", "就旅行"),
    QUNAR("512", "去哪儿"),
    CTRIPHTALADDIN("1024", "携程H5阿拉丁"),
    CTRIPV2("ctrip", "携程");

    // 供应商标识
    private String index;

    // 供应商描述
    private String name;

    EnumTP(String index, String name) {
        this.index = index;
        this.name = name;
    }

    // 获取供应商中文名称
    public static String getName(String index) {
        for (EnumTP enumTP:EnumTP.values()) {
            if (index.equals(enumTP.index)) {
                return enumTP.name;
            }
        }
        return "未知";
    }
}
