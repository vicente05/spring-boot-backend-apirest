package tresbits.springbootbackendapirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Collectors;
import javax.validation.Valid;

import java.util.*;
import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import tresbits.springbootbackendapirest.database.entity.Cliente;
import tresbits.springbootbackendapirest.database.entity.Region;
import tresbits.springbootbackendapirest.services.ClienteServiceImpl;
import tresbits.springbootbackendapirest.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private IUploadFileService uploadService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Cliente> findAllPage(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4, Sort.by("nombre"));
        return clienteService.findAll(pageable);
    }

    @GetMapping("/page/{page}/{take}")
    public Page<Cliente> findAllPage(@PathVariable Integer page, @PathVariable Integer take) {
        Pageable pageable = PageRequest.of(page, take, Sort.by("nombre"));
        return clienteService.findAll(pageable);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Cliente> findAllName(@PathVariable String nombre) {
        return clienteService.findAllQuery(nombre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        cliente = clienteService.findById(id);
        if (cliente == null) {
            String message = "El cliente ID: " + id + " no existe en la base de datos!";
            response.put("mensaje", message);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        clienteNew = clienteService.save(cliente);
        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable long id) {
        Cliente clienteActual = clienteService.findById(id);
        Cliente clienteUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual == null) {
            String message = "Error: no se pudo editar, el cliente ID: " + id + " no existe en la base de datos!";
            response.put("mensaje", message);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        clienteService.merge(cliente, clienteActual);
        clienteUpdate = clienteService.save(clienteActual);

        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", clienteUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable long id) {

        Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteService.findById(id);
        String nombreFotoAnterior = cliente.getFoto();

        uploadService.eliminar(nombreFotoAnterior);

        clienteService.delete(id);
        response.put("mensaje", "El cliente eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();
        String nombreArchivo = "";

        try {
            Cliente cliente = clienteService.findById(id);

            if (archivo.isEmpty()) {
                response.put("error", "El archivo esta vacio");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

            nombreArchivo = uploadService.copiar(archivo);

            String nombreFotoAnterior = cliente.getFoto();

            uploadService.eliminar(nombreFotoAnterior);

            cliente.setFoto(nombreArchivo);
            clienteService.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "Ha subido correctamente la imagen: " + nombreArchivo);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (IOException e) {
            response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
            response.put("error", e.getMessage() + ": " + e.getCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<?> verFoto(@PathVariable String nombreFoto) {

        Map<String, Object> response = new HashMap<>();
        Resource recurso = null;

        try {

            recurso = uploadService.cargar(nombreFoto);

            HttpHeaders cabecera = new HttpHeaders();
            cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

            return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

        } catch (MalformedURLException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/regiones")
    public List<Region> listarRegiones() {
        return clienteService.findAllRegiones();
    }

}
