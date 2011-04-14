/**
 * Copyright (C) 2011, FuseSource Corp.  All rights reserved.
 * http://fusesource.com
 *
 * The software in this package is published under the terms of the
 * CDDL license a copy of which has been included with this distribution
 * in the license.txt file.
 */
package org.fusesource.fabric.commands;

import java.util.List;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.fusesource.fabric.api.ZooKeeperClusterService;

@Command(name = "zk-quorum", scope = "fabric")
public class ZkCluster extends OsgiCommandSupport {

    private ZooKeeperClusterService service;

    @Option(name = "--add")
    private boolean add;

    @Option(name = "--remove")
    private boolean remove;

    @Argument(required = false, multiValued = true)
    private List<String> agents;

    public ZooKeeperClusterService getService() {
        return service;
    }

    public void setService(ZooKeeperClusterService service) {
        this.service = service;
    }

    @Override
    protected Object doExecute() throws Exception {
        if (agents == null || agents.isEmpty()) {
            System.out.println("ZooKeeper agents: ");
            for (String agent : service.getClusterAgents()) {
                System.out.println("  " + agent);
            }
        } else if (add) {
            service.addToCluster(agents);
        } else if (remove) {
            service.removeFromCluster(agents);
        } else {
            service.createCluster(agents);
        }
        return null;
    }

}
