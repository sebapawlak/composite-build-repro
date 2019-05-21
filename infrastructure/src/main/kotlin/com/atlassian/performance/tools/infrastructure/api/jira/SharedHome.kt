package com.atlassian.performance.tools.infrastructure.api.jira

import com.atlassian.performance.tools.infrastructure.api.os.Ubuntu
import com.atlassian.performance.tools.ssh.api.SshConnection

class SharedHome(
    private val ip: String,
    val remoteSharedHome: String
) {
    val localSharedHome = "jira-shared-home"
    private val ubuntu = Ubuntu()

    fun mount(connection: SshConnection) {
        ubuntu.install(connection, listOf("nfs-common"))
        connection.execute("mkdir -p $localSharedHome")
        connection.execute("sudo mount -o soft,intr,rsize=8192,wsize=8192 $ip:$remoteSharedHome $localSharedHome")
        connection.execute("sudo chown ubuntu:ubuntu $localSharedHome")
    }
}