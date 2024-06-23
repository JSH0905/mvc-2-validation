package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 글로벌오류(Object Error)를 위해 @ScriptAssert()를 사용하는데 더이상 스프링에서 자바스크립트 런타임을 지원하지 않음
 * 임시방편으로 build.gradle -> org.openjdk.nashorn:nashorn-core:15.3 의존성 추가
 * 하지만 ScriptAssert 보다 기존에 했었던 순수 자바코드를 더 선호함.
 */
@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총 합이 10000원 넘게 입력해주세요")
public class Item {

    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min=1000, max=1000000)
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
