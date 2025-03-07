package world.tan_xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.IllnessMedicine;


@RestController
@RequestMapping("illness_medicine")
public class IllnessMedicineController extends BaseController<IllnessMedicine> {

}
