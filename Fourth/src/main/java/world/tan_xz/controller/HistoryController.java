package world.tan_xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.History;



@RestController
@RequestMapping("history")
public class HistoryController extends BaseController<History> {

}
