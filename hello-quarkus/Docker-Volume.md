# Doubts about docker-compose
### Question 1
Q: Can you create a docker-compose.yaml file that has an external volume?
A: An external volume allows you to store data outside of the container, so that the data can persist even if the container is deleted or recreated.

An example docker-compose.yaml file that includes an external volume:

```yaml
version: '3'
 
services:
  myapp:
    image: myapp:latest
    volumes:
      - mydata:/app/data

volumes:
  mydata:
    external: true
```
In this example, the myapp service uses the mydata volume to store data in the /app/data directory within the container. The volumes section defines the mydata volume, and specifies that it is an external volume using the external: true property. This means that the volume is not created by Docker Compose, but is instead an existing volume that is mounted into the container.

To use this docker-compose.yaml file, you can run the following command to create the external volume:
```
docker volume create mydata
```

This will create a Docker volume named mydata, which can be mounted into containers using the external: true property in the _docker-compose.yaml_ file.

Then, you can start the containers using the docker-compose up command, and the myapp container will use the mydata volume to store data outside of the container.

### Question 2
Q: Do I need to create a "mydata" folder on my computer to create the volume? Or is it not necessary?
A: No, you don't need to create a folder named "mydata" on your computer to create the Docker volume. When you create a Docker volume using the docker volume create command, Docker will create the volume as a named volume within Docker's storage system.

This means that you don't need to create any directories or files on your host system to use the volume. Instead, you can mount the volume into your Docker containers using the volumes section of your docker-compose.yaml file, as I showed in my previous answer.

When you start a container that uses the mydata volume, Docker will automatically create any necessary directories within the container's filesystem to mount the volume at the specified path. So, in the example I provided earlier, when you start the myapp container, Docker will create a directory named /app/data within the container's filesystem, and will mount the mydata volume at that path.

This allows you to store data outside of the container, without needing to create any directories or files on your host system.

### Question 3
Q: Where "mydata" is located in my computer in this case?
A: When you create a Docker volume using the docker volume create command, the volume is created within Docker's storage system, which is managed by Docker itself. The data in the volume is not stored in a specific location on your computer's filesystem, but rather is stored within Docker's storage system.

When you mount the mydata volume into a Docker container using a docker-compose.yaml file, Docker will create a directory within the container's filesystem to mount the volume at the specified path. In the example I provided earlier, Docker will create a directory named /app/data within the container's filesystem, and will mount the mydata volume at that path.

If you want to inspect the contents of the mydata volume on your computer, you can use the docker volume inspect command to view information about the volume, including its location on your computer's filesystem. Here's an example of how you can use the command:

```
docker volume inspect mydata
This command will display information about the mydata volume, including its name, driver, and options. The output will also include a Mountpoint property, which specifies the location of the volume on your computer's filesystem.
```

However, note that the Mountpoint property will only be available if the volume has been mounted by a container. If the volume is not currently in use by any containers, the Mountpoint property will not be available.

### Question 4
Q: How do I create a volume in a specific folder on my computer?
A: To create a Docker volume in a specific folder on your computer, you can use a bind mount to mount a directory on your host system into a container as a volume. A bind mount allows you to map a directory on your host system to a directory in a container, effectively creating a volume that is backed by the directory on your host system.

Here's an example of how you can create a bind mount in your docker-compose.yaml file:
```yaml
version: '3'

services:
  myapp:
    image: myapp:latest
    volumes:
      - ./mydata:/app/data
```
In this example, the volumes section specifies a bind mount that maps the ./mydata directory on your host system to the /app/data directory in the myapp container. When you start your containers using docker-compose up, Docker will create the myapp container and mount the ./mydata directory on your host system into the container at the /app/data path.

Note that in this case, the ./mydata directory on your host system must exist before you start your containers. If the directory does not exist, Docker will create it as an empty directory, which may not be what you want.

Also note that bind mounts do not have the same level of isolation as named volumes, since they are backed by a directory on your host system. This means that changes made to the files in the bind mount will be visible on your host system, and changes made to the files on your host system will be visible in the container. If you need stronger isolation between the container and the host system, you may want to consider using a named volume instead.