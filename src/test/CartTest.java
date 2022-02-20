import com.shop.myapp.dto.*;
import com.shop.myapp.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CartTest {

    @Autowired
    private CartService cartService;

    @Test
    public void insertCart(){
        Cart cart = new Cart();
        cart.setMemberId("test2");
        cart.setOptionCode("40");
        cart.setAmount(1);
        int result = cartService.insertCart(cart);
        assertEquals(result,1);

    }

    @Test
    public void findCartDetailByMemberId(){
        List<Cart> cartDetails = cartService.findCartDetailByMemberId("test2");
        for (Cart cartDetail : cartDetails){
            System.out.println(cartDetail.toString());
        }
    }

    @Test
    public void findSelectCardByCartIds(){
        List<String> cartIds = Arrays.asList("45","46");
        List<Cart> carts = cartService.findSelectCartByCartIds(cartIds);
        for (Cart cart : carts){
            System.out.println(cart.toString());
        }
    }
}
