package bpmn2.storm.backend.persistence

import bpmn2.storm.backend.modelве.Diagram
import org.springframework.data.repository.CrudRepository

interface DiagramRepository : CrudRepository<Diagram, String>