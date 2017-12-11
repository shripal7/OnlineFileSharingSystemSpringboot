const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:3001/users'

const headers = {
    'Accept': 'application/json'
};

export const doLogin = (payload) =>
    fetch(`${api}/login`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    }).then(res => {
        return res.json()
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });
export const doSignUp = (payload) =>
    fetch(`${api}/signup`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
        body: (payload)
    }).then(res => {
        return res.status;
    })
        .catch(error => {
            console.log("This is error");
            return error;
        });

export const doLogout = () =>
    fetch(`${api}/logout`)
        .then(res => res.status)
        .catch(error => {
            console.log("This is error.");
            return error;
        });


      export const createFolder = (payload) =>
            fetch(`${api}/createfolder`, {
                method: 'POST',
                headers: {
                    ...headers,
                    'Content-Type': 'application/json'
                },
                body: (payload)
            }).then(res => {
                return res.status;
            }).catch(error => {
                console.log("This is error in create folder");
                return error;
            });

            export const createSharedFolder = (payload) =>
                fetch(`${api}/createSfolder`, {
                    method: 'POST',
                    headers: {
                        ...headers,
                        'Content-Type': 'application/json'
                    },
                    body: (payload)
                }).then(res => {
                    return res.status;
                }).catch(error => {
                    console.log("This is error in creating Shared folder");
                    return error;
                });

                export const deleteFile = (payload) =>
                    fetch(`${api}/delete`, {
                        method: 'POST',
                        headers: {
                            ...headers,
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(payload)
                    }).then(res => {
                        return res.status;
                    }).catch(error => {
                        console.log("This is error in deleting File");
                        return error;
                    });

                    export const uploadFile = (payload) =>
                        fetch(`${api}/upload`, {
                            method: 'POST',
                            body: payload
                        }).then(res => {
                            return res.status;
                        }).catch(error => {
                            console.log("This is error");
                            return error;
                        });
                    export const getImages = () =>
                        fetch(`${api}/listfiles`)
                            .then(res => res.json())
                            .catch(error => {
                                console.log("This is error.");
                                return error;
                            });

                    export const getImages = () =>
                      fetch(`${api}/grouplist`)
                          .then(res => res.json())
                          .catch(error => {
                          console.log("This is error.");
                          return error;
                        });
