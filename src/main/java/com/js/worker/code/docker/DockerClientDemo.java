package com.js.worker.code.docker;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DockerClientBuilder;

public class DockerClientDemo {
	
    /**
     * 连接docker服务器
     * @return
     */
	public DockerClient connectDocker(){
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.171.54:16666").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        return dockerClient;
    }
	
    /**
     * 创建容器
     * @param client
     * @return
     */
    public CreateContainerResponse createContainers(DockerClient client,String containerName,String imageName){
        //映射端口8088—>80
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8088));

        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(new HostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
        return container;
    }
	
	 /**
     * 启动容器
     * @param client
     * @param containerId
     */
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }
    
    
    public static void main(String[] args){
        DockerClientDemo dockerClientService =new DockerClientDemo();
        //连接docker服务器
        DockerClient client = dockerClientService.connectDocker();
        //创建容器
        CreateContainerResponse container = dockerClientService.createContainers(client,"helloWorld","docker.io/hello-world");
        //启动容器
        dockerClientService.startContainer(client,container.getId());
//      dockerClientService.startContainer(client,"13d4b81af470");
    }

}
