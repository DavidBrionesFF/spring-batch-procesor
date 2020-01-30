package com.bytecode.springbatch.proccesing;

import com.bytecode.springbatch.model.Persona;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

//Ahora vamos a procesar de un dato a otro, en esta seccion no lo hacemos solo mostramos un log
public class PersonaProcessor implements ItemProcessor<Persona, Persona> {
    private Log log = LogFactory.getLog(getClass());
    @Override
    public Persona process(Persona persona) throws Exception {
        log.info(String.format("PERSONA: {nombre: %s, apellido: %s, edad: %d}", persona.getNombre(), persona.getApellido(), persona.getEdad()));
        return persona;
    }
}
