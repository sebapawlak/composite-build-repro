package com.test

import com.amazonaws.regions.Regions
import com.atlassian.performance.tools.aws.api.Aws
import com.atlassian.performance.tools.aws.api.Investment
import com.atlassian.performance.tools.awsinfrastructure.api.InfrastructureFormula
import com.atlassian.performance.tools.awsinfrastructure.api.jira.DataCenterFormula
import com.atlassian.performance.tools.awsinfrastructure.api.virtualusers.StackVirtualUsersFormula
import com.atlassian.performance.tools.infrastructure.api.database.MySqlDatabase
import com.atlassian.performance.tools.infrastructure.api.dataset.Dataset
import com.atlassian.performance.tools.infrastructure.api.dataset.HttpDatasetPackage
import com.atlassian.performance.tools.infrastructure.api.distribution.PublicJiraSoftwareDistribution
import com.atlassian.performance.tools.infrastructure.api.jira.JiraHomePackage
import java.io.File
import java.net.URI
import java.time.Duration

class SomeTest {
    private val aws = Aws.Builder(region = Regions.EU_CENTRAL_1).build()
    private val dataset = Dataset(
            label = "label",
            database = MySqlDatabase(
                    HttpDatasetPackage(
                            uri = URI(""),
                            downloadTimeout = Duration.ofMillis(10)
                    )
            ),
            jiraHomeSource = JiraHomePackage(
                    HttpDatasetPackage(
                            uri = URI(""),
                            downloadTimeout = Duration.ofMillis(10)
                    )
            )
    )

    private val investment = Investment(
            useCase = "Catch Jira Software regressions",
            lifespan = Duration.ofHours(4)
    )

    val test = InfrastructureFormula(
            investment = investment,
            jiraFormula = DataCenterFormula.Builder(
                    productDistribution = PublicJiraSoftwareDistribution("8.0.0"),
                    jiraHomeSource = dataset.jiraHomeSource,
                    database = dataset.database
            ).build(),
            virtualUsersFormula = StackVirtualUsersFormula.Builder(
                    shadowJar = File(".")
            ).build(),
            aws = aws
    )
}
