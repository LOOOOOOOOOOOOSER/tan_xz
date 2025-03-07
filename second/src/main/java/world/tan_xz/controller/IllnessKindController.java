package world.tan_xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.IllnessKind;


@RestController
@RequestMapping("illness_kind")
public class IllnessKindController extends BaseController<IllnessKind> {

}
