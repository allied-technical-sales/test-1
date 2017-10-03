var Readable = require('stream').Readable,
    tape = require('tape'),
    head = require('./index.js');

function linesStream(n, lineEnd) {
    var stream = new Readable();

    if (typeof lineEnd === 'undefined') {
        lineEnd = '\n';
    }

    for (var i = 0; i < n; i++) {
        stream.push('line '+(i+1)+' of '+n+lineEnd);
    }

    stream.push(null);

    return stream;
}

function getStreamLines(stream, callback) {
    var lines = [];

    stream
        .on('data', function(line) {
            lines.push(line.toString());
        })
        .on('end', function() {
            callback(lines);
        });
}

tape('basic usage', function(t) {
    var s = linesStream(20).pipe(head({ head: 3, tail: 2 }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n',
            '...\n',
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('multiline message', function(t) {
    var s = linesStream(20).pipe(head({
        head: 3,
        tail: 2,
        message: [
            '## hiding long output...',
            '## run with different options to see a detailed output'
        ]
    }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n',
            '## hiding long output...\n',
            '## run with different options to see a detailed output\n',
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('no message', function(t) {
    var s = linesStream(20).pipe(head({ head: 3, tail: 2, message: null }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n',
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('show only head', function(t) {
    var s = linesStream(20).pipe(head({ head: 3 }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n'
        ])
        t.end();
    });
});

tape('show only tail', function(t) {
    var s = linesStream(20).pipe(head({ tail: 3 }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 18 of 20\n',
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('no output', function(t) {
    var s = linesStream(20).pipe(head({}));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [])
        t.end();
    });
});

tape('head is larger than output size', function(t) {
    var s = linesStream(2).pipe(head({ head: 5, tail: 2, message: '... output hidden ...' }));

    // when the head is larger than the stream output lines, no message or
    // tail is shown.
    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 2\n',
            'line 2 of 2\n'
        ])
        t.end();
    });
});

tape('tail is larger than output size', function(t) {
    var s = linesStream(5).pipe(head({ head: 1, tail: 20, message: '... output hidden ...' }));

    // when the head is larger than the stream output lines, no message or
    // tail is shown.
    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 5\n',
            '... output hidden ...\n',
            'line 2 of 5\n',
            'line 3 of 5\n',
            'line 4 of 5\n',
            'line 5 of 5\n'
        ])
        t.end();
    });
});

tape('don\'t show message when head is 0', function(t) {
    var s = linesStream(20).pipe(head({ tail: 2, message: '... output hidden ...' }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('don\'t show message when tail is 0', function(t) {
    var s = linesStream(20).pipe(head({ head: 2, message: '... output hidden ...' }));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n'
        ])
        t.end();
    });
});

tape('short syntax with head and tail', function(t) {
    var s = linesStream(20).pipe(head(3, 2));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n',
            '...\n',
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('short syntax with head', function(t) {
    var s = linesStream(20).pipe(head(3));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20\n',
            'line 2 of 20\n',
            'line 3 of 20\n'
        ])
        t.end();
    });
});

tape('short syntax with tail as negative head', function(t) {
    var s = linesStream(20).pipe(head(-2));

    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 19 of 20\n',
            'line 20 of 20\n'
        ])
        t.end();
    });
});

tape('line stream input, without any newline characters', function(t) {
    var s = linesStream(20, '').pipe(head({ head: 3, tail: 2, lineStream: true }));

    // the output doesn't have any newlines, just as the input did
    getStreamLines(s, function(lines) {
        t.deepEquals(lines, [
            'line 1 of 20',
            'line 2 of 20',
            'line 3 of 20',
            '...',
            'line 19 of 20',
            'line 20 of 20'
        ])
        t.end();
    });
});
