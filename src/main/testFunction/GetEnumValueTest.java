
/**
 * Created by T440P on 2016/4/1.
 */
public class GetEnumValueTest {
    public static void main(String[] args){
        System.out.println(GsCommand.SPV.toString());
    }
}

enum GsCommand {
    EMPTY("-"),
    PV("pv"),
    SPV("spv"),
    ECOM("ecom"),
    EVENT("ev"),
    HEARTBEAT("hb"),
    MOUSE_CLICK("mc"),
    SITE_SEARCH("ss"),
    SITE_SEARCH_THROUGH("ct"),
    JUNCTION_POINT("jp"),
    IMPRESSION("impress"),
    AD_CLICK("click");

    private final String command;

    GsCommand(String command) {
        this.command = command;
    }

    public String command() {
        return command;
    }
}
