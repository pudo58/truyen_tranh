package vn.tholv.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tholv.web.core.base.controller.AbstractController;
import vn.tholv.web.core.base.entity.Story;

@RestController
@RequestMapping("/story")
public class StoryController extends AbstractController<Story,Integer> {
}
