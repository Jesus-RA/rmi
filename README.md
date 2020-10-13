# RMI SERVER, Stub_File is in file folder and Stub_Hello is in stub folder

### Ejecución rmi

**En Windows**
` start rmiregistry puerto `

**En linux y MAC**
` rmiregistry puerto `

### Ejecución del servidor

```
  java -Djava.rmi.server.hostname=IP_Pública_Servidor -Djava.security.policy=policy.txt -Djava.rmi.server.codebase=URL_Stub FileServer

```

### Ejecución del cliente

`
  java FileClient IP:PUERTO
`
