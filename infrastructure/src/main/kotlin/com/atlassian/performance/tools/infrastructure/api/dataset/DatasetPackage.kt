package com.atlassian.performance.tools.infrastructure.api.dataset

import com.atlassian.performance.tools.ssh.api.SshConnection

interface DatasetPackage {

    /**
     * @return remotely downloaded and unpacked path
     */
    fun download(
        ssh: SshConnection
    ): String
}