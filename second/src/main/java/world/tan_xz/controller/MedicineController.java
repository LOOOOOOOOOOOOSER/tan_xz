package world.tan_xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.Medicine;



@RestController
@RequestMapping("medicine")
public class MedicineController extends BaseController<Medicine> {

}
