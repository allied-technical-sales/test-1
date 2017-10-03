## head-tail-stream

Stream to only output the first or last lines of a stream,
similarly as the `head` and `tail` CLI commands.

### Installation

```bash
npm install head-tail-stream
```

### Usage

```js
var head = require('head-tail-stream');

data.pipe(head(3)).pipe(process.stdout);
/*
line 1 of 20
line 2 of 20
line 3 of 20
*/

data.pipe(head(-2)).pipe(process.stdout);
/*
line 19 of 20
line 20 of 20
*/

// both a head and a tail. same as `head(3, 2)`
data.pipe(head({ head: 3, tail: 2 })).pipe(process.stdout);
/*
line 1 of 20
line 2 of 20
line 3 of 20
...
line 19 of 20
line 20 of 20
*/

// head and tail with a custom message in between
var headStream = head({
    head: 3,
    tail: 2,
    // a string, array (for several lines) or null (no message)
    message: [
        '## long output was hidden',
        '## run with different options to see a detailed output'
    ]
});
data.pipe(headStream).pipe(process.stdout);
/*
line 1 of 20
line 2 of 20
line 3 of 20
## long output was hidden
## run with different options to see a detailed output
line 19 of 20
line 20 of 20
*/
```

### License

MIT license - http://www.opensource.org/licenses/mit-license.php
