package pl.mkramek.fdafinder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import pl.mkramek.fdafinder.api.model.FinderResponse;
import pl.mkramek.fdafinder.http.service.FinderService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = { "http://localhost:5173", "http://127.0.0.1:5173" })
public class FinderController {

    @Autowired
    private FinderService finderService;

    @GetMapping("/drugs")
    public FinderResponse getDrugsData(@RequestParam String manufacturer, @RequestParam @Nullable String brand, @RequestParam @Nullable Integer limit) {
        return finderService.handleRequest(manufacturer, brand, limit);
    }
}
