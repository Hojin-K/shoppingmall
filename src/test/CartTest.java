import com.shop.myapp.dto.*;
import com.shop.myapp.service.CartService;
import com.shop.myapp.service.ItemOptionService;
import com.shop.myapp.service.ItemService;
import com.shop.myapp.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//@Transactional
public class CartTest {

    @Autowired
    private CartService cartService;

    @Test
    public void insertCart(){
        Cart cart = new Cart();
        cart.setMemberId("test2");
        cart.setItemOptionCode("20");
        cart.setItemAmount(1);
        int result = cartService.insertCart(cart);
        assertEquals(result,1);

    }

    @Test
    public void findCartDetailByMemberId(){
        List<CartDetail> cartDetails = cartService.findCartDetailByMemberId("test2");
        for (CartDetail cartDetail : cartDetails){
            System.out.println(cartDetail.getItemOption().getOptionName());
        }
    }
}
