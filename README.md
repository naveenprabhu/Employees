# ME Employees APP

Employees app lists the users bio and their photo

##Employees List Screen:

![alt text](https://github.com/naveenprabhu/Employees/blob/master/res/images/Employee_List.png)

## Employess Detail Screen:

![alt text](https://github.com/naveenprabhu/Employees/blob/master/res/images/Employee_Detail.png)

## Development 


Install Android Studio

	http://developer.android.com/sdk/index.html

Install Android SDK & buildtools

	Android 6.0 (API 23)
	buildtools 23.0.1 and above

Open workspace

	Android Project is located under Employees/clients/android
	Android Studio -> File -> Open

Run Unit Tests

	$ ./gradlew build

Build project

	$ gradlew build
    
# Libraries used:

*	RxAndroid

*	Retrofit

*	OkHttp

*	Dagger2

*	Mockito

*	Timber

*	Expresso

*	GSON

    
## Connect to local service    

```
├── README.md
├── clients/
|   ├── android/
│   └── ios/
├── people.raml
├── res/
│   └── images/
└── servers/
    └── people/
        ├── images/
        └── ppl_server.py
```

-   Make sure you have Python 3.5 or higher version installed

-   Install python packages using `pip` and `requirements.txt` file.

```
$ cd PROJECT_DIR
$ cd servers/people
$ pip install -r requirements.txt
```

### Running People Server

```
$ cd PROJECT_DIR
$ cd servers/people
$ python3 ppl_server.py

```

You should see output as follow:

```
* Serving Flask app "ppl_server" (lazy loading)
 * Environment: development
 * Debug mode: on
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 123-168-005
```

Checking server's status

```
$ curl http://127.0.0.1:5000/healthCheck
"Ok"
```

Retrieve employee's details.

```
$ curl http://127.0.0.1:5000/employees/1

{
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "gender": "male",
    "birth_date": "01 January 1980",
    "thumbImage": "...",
    "image": "/9j/4AAQSkZJRgABAgEAZABkAAD/4RssRXhpZgAATU0AKgAAAAgABwESAAMAAAABAAEAAAEaAAUA\nAAABAAAAYgEbAAUAAAABAAAAagEoAAMAAAABAAIAAAExAAIAAAAcAAAAcgEyAAIAAAAUAAAAjodp\nAAQAAAABAAAApAAAANAAD0JAAAAnEAAPQkAAACcQQWRvYmUgUGhvdG9zaG9wIENTMyBXaW5kb3dz
    ..."
}
```
