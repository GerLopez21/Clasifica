package Grupo11.ProyectoEgg.Servicios;

import Grupo11.ProyectoEgg.Entidad.Materiales;
import Grupo11.ProyectoEgg.Repositorio.MaterialesRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialesServicio {

    @Autowired
    private MaterialesRepositorio materialesRepositorio;

    @Transactional
    public void crear(Long carton, Long plastico, Long vidrio, Long latas, Long papel) {

        Materiales materiales = new Materiales();

        materiales.setCarton(carton);
        materiales.setPlastico(plastico);
        materiales.setLatas(latas);
        materiales.setVidrio(vidrio);
        materiales.setPapel(papel);

        setearMateriales(carton, plastico, vidrio, latas, papel);

        materialesRepositorio.save(materiales);
    }

    public void setearMateriales(Long carton, Long plastico, Long vidrio, Long latas, Long papel) {

        Materiales materiales = new Materiales();

        if (carton == null) {
            materiales.setCarton((long) 0);
        }

        if (plastico == null) {
            materiales.setPlastico((long) 0);
        }

        if (vidrio == null) {
            materiales.setVidrio((long) 0);
        }

        if (latas == null) {
            materiales.setLatas((long) 0);
        }

        if (papel == null) {
            materiales.setPapel((long) 0);
        }
    }

    @Transactional
    public void modificar(Long carton, Long plastico, Long latas, Long vidrio, Long papel) {
        materialesRepositorio.modificar(carton, plastico, latas, vidrio, papel);
    }

    @Transactional//Indicamos que no me va a modificar la BD solo la va a leer, no es obligatorio
    public List<Materiales> buscarTodos() {
        List<Materiales> materiales = materialesRepositorio.findAll();
        return materiales;
    }

    @Transactional
    public void eliminar(Long id) {
        materialesRepositorio.deleteById(id);
    }

}
