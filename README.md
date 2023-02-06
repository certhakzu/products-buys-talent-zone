# Reto Backend Talent Zone Productos-Compras

## Instrucciones:

* Para ejecutar y probar el proyecto, descarque o clone la rama 'main' del repositorio https://github.com/certhakzu/products-buys-talent-zone.git
* Asegurese de teer instalado JAVA 17 y Gradle en su equipo local.
* Compile y ejecute el proyecto (de preferencia en el IDE IntelliJ).
* Para probar la API, importe desde Postman el archivo con la colección de consultas para la misma que se encuentra en la raiz del proyecto y tiene como nombre 'Productos_Compras_postman_collection.json'. Debería verse algo asi:

![](images/img_5.png)

Una vez hecha la importación, puede consumirla de la siguiente manera:
    
### Find All Products
![](images/img.png)

### Find a Product
![](images/img_1.png)

### Create a Product
![](images/img_2.png)

### Update a Product
![](images/img_3.png)

### Delete a Product
![](images/img_4.png)

### Find All Buys
![](images/img_6.png)

### Find a Buy
![](images/img_7.png)

### Create a Buy
![](images/img_8.png)

### Update a Buy
![](images/img_9.png)

### Delete a Buy
![](images/img_10.png)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

Se usa el plugin de bancolombia para arquitectura hexagonal de manera reaciva con WebFlux.

