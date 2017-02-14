import java.util.HashMap;
import java.util.Map;

public class getIntEnumValueTest {
    public static void main(String[] args){
        System.out.println(MatchType.REGEX_MATCH.getId().toString());
    }
}

enum MatchType {
    /**
     * 精确匹配。
     */
    EXACT_MATCH(0),

    /**
     * 以指定字符串开始。
     */
    BEGINS_WITH(1),

    /**
     * 以指定字符串结束。
     */
    ENDS_WITH(2),

    /**
     * 包含特定字符串。
     */
    CONTAINS(3),

    /**
     * 正则匹配
     */
    REGEX_MATCH(4),

    /**
     * 属于集合中的一项
     */
    IN(5),

    /**
     * 不等于。（!EXACT_MATCH）
     */
    NOT_EQUALS(10),

    /**
     * 不以指定字符串开始。（!BEGINS_WITH）
     */
    NOT_BEGINS_WITH(11),

    /**
     * 不以指定字符串结束。(!ENDS_WITH)
     */
    NOT_ENDS_WITH(12),

    /**
     * 不包含特定字符串。(!CONTAINS)
     */
    NOT_CONTAINS(13),

    /**
     * 不满足此正则匹配。(!REGEX_MATCH)
     */
    NOT_REGEX_MATCH(14),

    /**
     * 不属于集合中的任何一项。(!IN)
     */
    NOT_IN(15),

    /**
     * 数值类型相等
     */
    EQUAL_TO(20),

    /**
     * 数值类型大于
     */
    GREATER_THAN(21),

    /**
     * 数值类型小于
     */
    LESS_THAN(22),

    /**
     * 数值类型不等于
     */
    NOT_EQUAL_TO(23),

    /**
     * 数值类型大于等于
     */
    GREATER_THAN_OR_EQUAL(24),

    /**
     * 数值类型小于等于
     */
    LESS_THAN_OR_EQUAL(25);



    private static final Map<Integer,MatchType> lookup = new HashMap<Integer,MatchType>();

    static {
        for(MatchType matchType: MatchType.values()) {
            lookup.put(matchType.getId(), matchType);
        }
    }

    private Integer id;

    MatchType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public static MatchType getById(Integer id) {
        MatchType matchType = lookup.get(id);
        if(matchType != null) {
            return matchType;
        }
        throw new IllegalArgumentException("invalid value for MatchType: " + id);
    }
}
