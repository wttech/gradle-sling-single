import com.cognifide.gradle.sling.common.instance.local.Source
import com.cognifide.gradle.sling.common.instance.local.OpenMode
import com.neva.gradle.fork.ForkExtension

configure<ForkExtension> {
    properties {
        define("Instance", mapOf(
                "instanceType" to {
                    label = "Type"
                    select("local", "remote")
                    description = "Local - instance will be created on local file system\nRemote - connecting to remote instance only"
                    controller { toggle(value == "local", "instanceRunModes", "instanceJvmOpts", "localInstance*") }
                },
                "instanceMasterHttpUrl" to {
                    label = "HTTP URL"
                    url("http://localhost:8080")
                    optional()
                    description = "For accessing instance"
                },
                "instanceProvisionEnabled" to {
                    label = "Provision Enabled"
                    description = "Turns on/off automated instance configuration."
                    checkbox(true)
                },
                "instanceProvisionDeployPackageStrict" to {
                    label = "Provision Deploy Package Strict"
                    description = "Check if package is actually deployed on instance.\n" +
                            "By default faster heuristic is used which does not require downloading deployed packages eagerly."
                    checkbox(false)
                },
                "instanceAwaitUpHelpEnabled" to {
                    label = "Await Up Helping"
                    description = "Tries to start bundles automatically when instance is not stable longer time"
                    checkbox(true)
                }
        ))

        define("Local instance", mapOf(
                "localInstanceSource" to {
                    label = "Source"
                    description = "Controls how instances will be created (from scratch, backup or any available source)"
                    select(Source.values().map { it.name.toLowerCase() }, Source.AUTO.name.toLowerCase())
                },
                "localInstanceStarterJarUri" to {
                    label = "Starter URI"
                    description = "Dependency notation or URL to JAR (e.g Sling Starter or Composum Pages Starter)"
                    text("org.apache.sling:org.apache.sling.starter:11")
                },
                "localInstanceBackupDownloadUri" to {
                    label = "Backup Download URI"
                    description = "For backup file. Protocols supported: SMB/SFTP/HTTP"
                    optional()
                },
                "localInstanceBackupUploadUri" to {
                    label = "Backup Upload URI"
                    description = "For directory containing backup files. Protocols supported: SMB/SFTP"
                    optional()
                },
                "localInstanceRunModes" to {
                    label = "Run Modes"
                    text("local")
                },
                "localInstanceJvmOpts" to {
                    label = "JVM Options"
                    text("-server -Xmx2048m -XX:MaxPermSize=512M -Djava.awt.headless=true")
                },
                "localInstanceOpenMode" to {
                    label = "Open Automatically"
                    description = "Open web browser when instances are up."
                    select(OpenMode.values().map { it.name.toLowerCase() }, OpenMode.ALWAYS.name.toLowerCase())
                },
                "localInstanceOpenPath" to {
                    label = "Open Path"
                    text("/")
                }
        ))

        define("Package", mapOf(
                "packageDeployAvoidance" to {
                    label = "Deploy Avoidance"
                    description = "Avoids uploading and installing package if identical is already deployed on instance."
                    checkbox(true)
                },
                "packageValidatorEnabled" to {
                    label = "Validator Enabled"
                    description = "Turns on/off package validation using OakPAL."
                    checkbox(true)
                },
                "packageNestedValidation" to {
                    label = "Nested Validation"
                    description = "Turns on/off separate validation of built subpackages."
                    checkbox(true)
                },
                "packageBundleTest" to {
                    label = "Bundle Test"
                    description = "Turns on/off running tests for built bundles put under install path."
                    checkbox(true)
                }
        ))

        define("Authorization", mapOf(
                "companyUser" to {
                    label = "User"
                    description = "Authorized to access Sling files"
                    defaultValue = System.getProperty("user.name").orEmpty()
                    optional()
                },
                "companyPassword" to {
                    label = "Password"
                    description = "For above user"
                    optional()
                },
                "companyDomain" to {
                    label = "Domain"
                    description = "Needed only when accessing Sling files over SMB"
                    defaultValue = System.getenv("USERDOMAIN").orEmpty()
                    optional()
                }
        ))

        define("Release", mapOf(
                "releaseRepository" to {
                    label = "Repository URL"
                    description = "Nexus, Artifactory etc."
                    defaultValue = "https://nexus.company.com/content/repositories/company-internal"
                    optional()
                },
                "releaseUser" to {
                    label = "User"
                    description = "Authorized to release artifacts to above repository"
                    optional()
                },
                "releasePassword" to {
                    label = "Password"
                    description = "For above user"
                    optional()
                }
        ))

        define("Other", mapOf(
                "notifierEnabled" to {
                    label = "Notifications"
                    description = "Controls displaying of GUI notifications (baloons)"
                    checkbox(true)
                }
        ))
    }
}
