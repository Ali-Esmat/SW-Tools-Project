# Setup instructions

From a Linux environment run the `create-sd.sh` script in `enterprise-java-project`:

```bash
cd enterprise-java-project/
./create-sd.sh
```

Alternatively,
Add the security domain in `standalone-full.xml`

```xml
<security-domain name="db" cache-type="default">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName"
                value="java:jboss/datasources/ExampleDS" />
            <module-option name="principalsQuery"
                value="select password from user where name=?" />
            <module-option name="rolesQuery"
                value="select role.name from user, role where user.name=? and role.id = user.roleId" />
        </login-module>
    </authentication>
</security-domain>
```

Alternatively, set these properties manually in the JBoss web console.
