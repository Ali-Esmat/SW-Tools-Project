#!/bin/bash
if [[ -z "${JBOSS_HOME}" ]];
then
    echo "Enter JBoss home"
    read jboss_path
    export JBOSS_HOME=$jboss_path
else
    echo "JBoss home received from env variable"
fi
echo "JBoss home: $JBOSS_HOME"

echo "Creating db security domain"
sh "$JBOSS_HOME/bin/jboss-cli.sh" --connect "/subsystem=security/security-domain=db/:add(cache-type=default)" || exit 1
sh "$JBOSS_HOME/bin/jboss-cli.sh" --connect "/subsystem=security/security-domain=db/authentication=classic:add\
(login-modules=[{'code'=>'Database', 'flag'=>'required', \
'module-options'=>['dsJndiName'=>'java:jboss/datasources/ExampleDS', \
'principalsQuery'=>'select password from user where name=?', \
'rolesQuery'=>'select role.name from user, role where user.name=? and role.id = user.roleId'] }])" || exit 1
sh "$JBOSS_HOME/bin/jboss-cli.sh" --connect ":reload" || exit 1
