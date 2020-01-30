# Spring-Batch-Procesor
Procesando datos por lote con Spring Batch

## Spring Batch
Es un modulo de Spring Boot que nos permite procesar datos por lotes, lo cual hace que sea muy practico y muy facil de usar.
Ademas de ser Spring que nos permite la integracion de multiples modulos del mismo framework, como en este caso que usamos Spring Data MongoDB
para persistir los lotes de informacion.

Para entender como funciona Spring Batch definimos lo siguiente:

1. JobRepository: Es el repositorio principal donde se encuentran los trabajos a ejecutar
2. JobLauncher: Es quien se encarga de ejecutar los trabajos
3. Job: Es el trabajo a ejecutar que se divide en pasos, y estos a su vez en lectura, proceso y escritura.
4. ItemReader: Es el encargado de leer los datos, ya sea que vienen de un csv, xml, json, api, base de datos, etc...
5. ItemProcesor: Una vez leemos los datos y son mapeados, podemos comenzar a procesarlos, ya sea analizarlos con redes neuronales o trasnformarlos etc...
6. ItemWriter: Este es el encargado de escribirlos en alguna parte, ya sea en un fichero, en una base de datos, etc...
7. Steep: Es el paso a ejecutar que a su vez se divide en los 3 anteriores.

De esta manera tenemos la siguiente estructura:

![Procesos Spring batch](https://docs.spring.io/spring-batch/docs/current/reference/html/images/spring-batch-reference-model.png)

* Documentacion Oficial [https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html](https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html) *
Si deseas aprender Spring te dejo un curso completo y gratuito!!
[CURSO DESAROLLO WEB CON SPRING BOOT GRATUITO](https://www.youtube.com/watch?v=H2TIvYIRxW4&list=PLcIHm18h1i4nD4H8tPeID8PNiKsm4VZm5)
