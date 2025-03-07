package world.tan_xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.Illness;



@RestController
@RequestMapping("illness")
public class IllnessController extends BaseController<Illness> {

}
