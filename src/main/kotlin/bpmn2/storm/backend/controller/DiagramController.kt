package bpmn2.storm.backend.controller

import bpmn2.storm.backend.modelве.Diagram
import bpmn2.storm.backend.persistence.DiagramRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/diagrams/")
class DiagramController(val repository: DiagramRepository) {

@GetMapping
fun findAll() = repository.findAll()

@PostMapping
fun addDiagram(@RequestBody diagram: Diagram) = repository.save(diagram)

@PutMapping("/{id]")
fun updateDiagram(@PathVariable id: UUID, @RequestBody diagram: Diagram) {
    assert(diagram.id == id)
    repository.save(diagram)
}

@DeleteMapping("/{id}")
fun removeDiagram(@PathVariable id: UUID)
        = repository.deleteById(id.toString())



@GetMapping("/{id}")
fun getById(@PathVariable id:UUID) = repository.findById(id.toString())

}