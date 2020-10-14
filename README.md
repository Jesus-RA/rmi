# RMI SERVER, FileImpl_Stub.class is in file folder and Hello_Stub.class is in hello folder

### Ejecución rmi

**En Windows**
` start rmiregistry puerto `

**En linux y MAC**
` rmiregistry puerto `

### Ejecución del servidor


> En la clase servidor se debe poner la IP local como dirección IP al registrar el objeto remoto y especificar la IP pública en la terminal en hostname.


```
  java -Djava.rmi.server.hostname=IP_Pública_Servidor -Djava.security.policy=policy.txt -Djava.rmi.server.codebase=URL_Stub FileServer
```

> *Al final de la URL es importante poner un '/' para que busque el archivo stub dentro del folder.*


`
  -Djava.rmi.server.codebase=https://github.com/Jesus-RA/rmi/tree/master/file/
`


### Ejecución del cliente

`
  java FileClient IP:PUERTO
`
