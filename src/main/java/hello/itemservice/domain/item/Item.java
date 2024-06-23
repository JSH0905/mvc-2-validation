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

    /**
     * Bean Validation의 한계 : 하나의 폼에서 검증방식을 여러가지로 하기로 했을 경우 문제가 생김
     * 상품 수정시에는 ID가 @NotNull 이라는 조건을 추가하면 상품등록시 문제가 발생함.
     * 해결방법 : 스프링에서 BeanValidation의 groups -> 실무에서는 잘 안씀 -> 실무에서는 Form 객체 분리 방법을 사용
     */

    @NotNull(groups = UpdateCheck.class) // 수정 요구사항 추가
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min=1000, max=1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
