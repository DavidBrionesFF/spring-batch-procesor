package com.bytecode.springbatch.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//Agregamos nuestro componente donde declararemos la tarea que se ejecutara cada 100,000 milisegundos
@Component
public class PersonTask {
    //Inyectamos el JobLauncher que se enecargara de ejcutar las tareas
    @Autowired
    private JobLauncher jobLauncher;

    //Inyectamos el trabajo a ejecutar
    @Autowired
    private Job job;

    //Agregamos la tarea y le pasamos el tiempo de ejecucion
    @Scheduled(fixedDelay = 100000)
    public void onProcces() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException,
            JobRestartException,
            JobInstanceAlreadyCompleteException {
        //Declaramos los parametros
        JobParameters jobParameters = new JobParametersBuilder()
                                        .addString("JobId", String.valueOf(System.currentTimeMillis()))
                                        .toJobParameters();
        ///Ejecutamos el trabajo
        jobLauncher.run(job, jobParameters);
    }
}
