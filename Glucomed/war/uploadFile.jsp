<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>


<html>
<head>
</head>
<body>
		 <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
        File: <input type="file" name="myFile">
      	  <input type="submit" value="Upload">
   			 </form>
</body>
</html>