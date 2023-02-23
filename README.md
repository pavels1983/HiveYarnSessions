# HiveYarnSessions

This application allows monitor Hive SQL queries and match them to YARN applications (including memory, cores and containers).

As you probably mentioned, standart HiveServer2 UI shows only SQL queries, but you can't monitor core/memory/containers usage. If you look to the YARN RM, it is hard to find exact application, related to specific Hive SQL query (in case if many queries were submitted by same user). This tool will help you.
Program runs as a local web server, and you can monitor queries and applications onlie, and kill them.

Usage:
1. Fill options in _app.properties_:
   - **server.http.port** - Local HTTP port to start on
   - **hive.server2.address.1** - HiveServer2 HTTP address #1 (fill #2,#3, etc. if needed)
   - **resourse.manager.address** - YARN resource manager HTTP address 
   - **timeline.server.address** - YARN Timeline server REST adress
2. Start class _AppsHttpService_
    