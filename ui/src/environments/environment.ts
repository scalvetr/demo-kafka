// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  messagesService: {
    postMessage: "http://demo-kafka.minkube/api/messages",
    getMessagesStream: "http://demo-kafka.minkube/api/messages/stream",
  }
};
