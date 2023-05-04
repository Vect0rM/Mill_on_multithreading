package com.vector.mill;

import com.vector.mill.bean.MillService;
import com.vector.mill.bean.MillState;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/mill")
public class MIllController {

    private final MillService millService;

    public MIllController(MillService millService) {
        this.millService = millService;
    }

    @GetMapping()
    public MillState state(){
        return millService.getState();
    }

    @PostMapping("water/{capacity}")
    public String addWater(@PathVariable Integer capacity){
        millService.addWater(capacity);
        return "OK";
    }
    @PostMapping("millet/{capacity}")
    public String addMillet(@PathVariable Integer capacity){
        millService.addMillet(capacity);
        return "OK";
    }
    @PostMapping("flour/drop")
    public String dropFlour(){
        millService.dropFlour();
        return "OK";
    }
}
