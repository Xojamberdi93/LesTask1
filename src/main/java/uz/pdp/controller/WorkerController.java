package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.payload.WorkerDto;
import uz.pdp.service.WorkerService;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return workerService.getAllWorkers();
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody WorkerDto workerDto){
        return workerService.add(workerDto);
    }
}
